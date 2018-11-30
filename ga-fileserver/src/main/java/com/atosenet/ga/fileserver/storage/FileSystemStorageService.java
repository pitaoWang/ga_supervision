package com.atosenet.ga.fileserver.storage;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileSystemStorageService implements StorageService {

	private final Path rootLocation;
	private final String defaultstorepath;

	@Autowired
	public FileSystemStorageService(StorageProperties properties) {
		this.rootLocation = Paths.get(properties.getLocation());
		this.defaultstorepath = properties.getDefaultstorepath();
	}

	@Override
	public String store(MultipartFile file, String path, String name) {
		try {
			if (file.isEmpty()) {
				throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
			}
			String storedFilename = null;
			String storedFilePath = null;
			
			if (StringUtils.isEmpty(name)) {
				String uuid = UUID.randomUUID().toString().replace("-", "");
				String ext = StringUtils.getFilenameExtension(file.getOriginalFilename());
				StringBuilder sb = new StringBuilder(uuid);
				sb.append(".").append(ext);

				storedFilename = sb.toString();
			} else {
				String ext = StringUtils.getFilenameExtension(file.getOriginalFilename());
				StringBuilder sb = new StringBuilder(name);
				sb.append(".").append(ext);
				storedFilename = sb.toString();
			}
			
			if(StringUtils.isEmpty(path)) {
				Date nowDate = new Date();
				String year = DateFormatUtils.format(nowDate, "yyyy");
				String month = DateFormatUtils.format(nowDate, "MM");
				String day = DateFormatUtils.format(nowDate, "dd");
				StringBuilder sb = new StringBuilder(this.defaultstorepath);
				sb.append(File.separator).append(year).append(File.separator).append(month).append(File.separator).append(day);
				path = sb.toString();
			} else {
				if(path.startsWith(File.separator)) {
					path = path.substring(1);
				}
			}
			
			Files.createDirectories(this.rootLocation.resolve(path));
			
			storedFilePath = path + File.separator + storedFilename;
			
			Files.copy(file.getInputStream(), this.rootLocation.resolve(storedFilePath),
					StandardCopyOption.REPLACE_EXISTING);
			return storedFilePath;
		} catch (IOException e) {
			e.printStackTrace();
			throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
		}
	}
	
	@Override
	public String storeDoc(MultipartFile file, String path, String name) {
		try {
			
			if (file.isEmpty()) {
				throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
			}
			String storedFilename = null;
			String storedFilePath = null;
			
			String uuid = UUID.randomUUID().toString().replace("-", "");
				String ext = StringUtils.getFilenameExtension(file.getOriginalFilename());
				StringBuilder sb = new StringBuilder(uuid);
				sb.append(".").append(ext);
				storedFilename = sb.toString();
			
			
			/*if(StringUtils.isEmpty(name)) {*/
				
				StringBuilder sbd = new StringBuilder(this.defaultstorepath);
				Date date = new Date();
				sbd.append(File.separator).append(path).append(File.separator).append(name)
				.append(File.separator).append(date.getTime())
				.append(File.separator).append(storedFilename);
				path = sbd.toString();
			/*} else {
				if(path.startsWith(File.separator)) {
					path = path.substring(1);
				}
			}*/
			
			Files.createDirectories(this.rootLocation.resolve(path));
			
			storedFilePath = path + File.separator + storedFilename;
			
			Files.copy(file.getInputStream(), this.rootLocation.resolve(storedFilePath),
					StandardCopyOption.REPLACE_EXISTING);
			return storedFilePath;
		} catch (IOException e) {
			e.printStackTrace();
			throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
		}
	}

	@Override
	public Stream<Path> loadAll() {
		try {
			return Files.walk(this.rootLocation, 1).filter(path -> !path.equals(this.rootLocation))
					.map(path -> this.rootLocation.relativize(path));
		} catch (IOException e) {
			throw new StorageException("Failed to read stored files", e);
		}

	}

	@Override
	public Path load(String filename) {
		return rootLocation.resolve(filename);
	}

	@Override
	public Resource loadAsResource(String filename) {
		try {			
			Path file = load(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new StorageFileNotFoundException("Could not read file: " + filename);

			}
		} catch (MalformedURLException e) {
			throw new StorageFileNotFoundException("Could not read file: " + filename, e);
		}
	}

	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}

	@Override
	public void init() {
		try {
			Files.createDirectories(rootLocation);
		} catch (IOException e) {
			throw new StorageException("Could not initialize storage", e);
		}
	}
}
