import router from "@/router/index.js";
//1000 ~ 1ms
export const urls =  {
    getHeader:"/api/admin/groups/getById?"
}
export function sleep(time) {
    return new Promise(resolve => setTimeout(resolve, time));
}
export function goto(url){
    router.push(url);
}
export function getMenu(url,params){
    console.log("getMenu");
    const pam = new URLSearchParams(params).toString();
    const res = new XMLHttpRequest()
    res.open("GET", url + pam,false);
    res.setRequestHeader("Content-Type", "application/json")
    try {
        res.send()
        if (res.status === 200){
            let json = JSON.parse(res.response)
            json.data = JSON.parse(json.data)
            json.aside = JSON.parse(json.aside)
            return json;
        }
    } catch(error) {
        console.log(error);
        return null;
    }
}
