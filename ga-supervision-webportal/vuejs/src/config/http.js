import {service, serviceHref,serviceLocationHref} from '@/utils/fetch'

export default {
    //get 请求
    async httpGet(url = '', data = {}){
        return service.get(url, {params: data});
    },
    async httpPost(url = '', data = {}){
        return service.post(url, data);
    },
    async jsonPost(url = '', data = {}){
        return service.post(url,data);
    },
    async httpDelete(url = '', data = {}){
        return service.delete(url,data);
    },
    httpHref(url = '', data = {}){
        serviceHref(url,data);
    },
    LocationHref(url = '', data = {}){
        serviceLocationHref(url,data);
    },

}
