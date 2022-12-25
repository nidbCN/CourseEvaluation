import axios from 'axios'

// create an axios instance
const requestHelper = axios.create({
    baseURL: "http://localhost:8080/", // url = base url + request url
    timeout: 5000 // request timeout
})

// request interceptor
requestHelper.interceptors.request.use(
    config => {
        // do something before request is sent

        return config
    },
    error => {
        // do something with request error
        console.log(error) // for debug
        return Promise.reject(error)
    }
)

// response interceptor
requestHelper.interceptors.response.use(
    /**
     * If you want to get http information such as headers or status
     * Please return  response => response
     */

    /**
     * Determine the request status by custom code
     * Here is just an example
     * You can also judge the status by HTTP Status Code
     */
    response => {
        const res = response.data

        // if the custom code is not 20000, it is judged as an error.
        if (res.code !== 200) {
            console.log("error with non-200 code:" + JSON.stringify(res));
        } else {
            return res
        }
    },
    error => {
        console.log('err' + error) // for debug

        return Promise.reject(error)
    }
)

export default requestHelper
