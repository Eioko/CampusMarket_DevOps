import axios from "axios"
import { getToken } from "@/utils/storage.js";
const URL_API = 'http://localhost:31451/api/campus-product-sys/v1.0'
const request = axios.create({
  baseURL: URL_API,
  timeout: 8000
});
//全局拦截器
request.interceptors.request.use(config => {
  let type = config.url.split("/")[1];
  console.log(type + " asdasd ");
  console.log(config.url + " asdasd ");

  // 根据路由判断,后台地址是哪个
  switch (type) {
    case "operation-log":
    case "user":
      config.baseURL = "http://localhost:31449/api/campus-product-sys/v1.0";
      break;
    case "category":
    case "product":
      config.baseURL = "http://localhost:31450/api/campus-product-sys/v1.0";
      break;
    default:
      config.baseURL = "http://localhost:31451/api/campus-product-sys/v1.0";
      break;
  }
  console.log(config.baseURL);

  const token = getToken();
  if (token !== null) {
    config.headers["token"] = token;
  }
  return config;
}, error => {
  return Promise.reject(error);
});
export default request;