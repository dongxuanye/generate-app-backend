## 开发日志

#### 部署前端html文件

##### 1.通过serve方式
```shell
 // 首先在根目录下安装 npm i -g serve
 
 // 然后在根目录下执行 serve，即可遍历所有目录
```
缺点：性能差，要单独启动一个服务器

##### 2.通过springBoot方式启动
```java
@RestController
@RequestMapping("/static")
public class StaticResourceController {

    // 应用生成根目录（用于浏览）
    private static final String PREVIEW_ROOT_DIR = System.getProperty("user.dir") + "/tmp/code_output";

    /**
     * 提供静态资源访问，支持目录重定向
     * 访问格式：http://localhost:8125/api/static/{deployKey}[/{fileName}]
     */
    @GetMapping("/{deployKey}/**")
    public ResponseEntity<Resource> serveStaticResource(
            @PathVariable String deployKey,
            HttpServletRequest request) {
        try {
            // 获取资源路径
            String resourcePath = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
            resourcePath = resourcePath.substring(("/static/" + deployKey).length());
            // 如果是目录访问（不带斜杠），重定向到带斜杠的URL
            if (resourcePath.isEmpty()) {
                HttpHeaders headers = new HttpHeaders();
                headers.add("Location", request.getRequestURI() + "/");
                return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
            }
            // 默认返回 index.html
            if (resourcePath.equals("/")) {
                resourcePath = "/index.html";
            }
            // 构建文件路径
            String filePath = PREVIEW_ROOT_DIR + "/" + deployKey + resourcePath;
            File file = new File(filePath);
            // 检查文件是否存在
            if (!file.exists()) {
                return ResponseEntity.notFound().build();
            }
            // 返回文件资源
            Resource resource = new FileSystemResource(file);
            return ResponseEntity.ok()
                    .header("Content-Type", getContentTypeWithCharset(filePath))
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * 根据文件扩展名返回带字符编码的 Content-Type
     */
    private String getContentTypeWithCharset(String filePath) {
        if (filePath.endsWith(".html")) return "text/html; charset=UTF-8";
        if (filePath.endsWith(".css")) return "text/css; charset=UTF-8";
        if (filePath.endsWith(".js")) return "application/javascript; charset=UTF-8";
        if (filePath.endsWith(".png")) return "image/png";
        if (filePath.endsWith(".jpg")) return "image/jpeg";
        return "application/octet-stream";
    }
}
```
通过这个方式可以访问这个网址<http://localhost:8125/api/static/multi_file_342537482388156416/>
可以得到预览效果

#### 3.通过nginx部署html应用
```conf
charset      utf-8;
charset_types text/css application/javascript text/plain text/xml application/json;
# 项目部署根目录
root         D:/javastudy/yupi/generate-app-backend/tmp/code_deploy;

# 处理所有请求
location ~ ^/([^/]+)/(.*)$ {
    try_files /$1/$2 /$1/index.html =404;
}

#location / {
#    root   html;
#    index  index.html index.htm;
#}

```
运行nginx -s reload命令，就可以访问自己部署的页面了
访问>http://localhost/1234/
优点是性能最好，但是缺点是需要额外配置一个nginx组件，但是生产环境中本来就要用nginx

#### 4.使用对象存储
使用对象存储cos的静态网页访问能力。同时实现存储+访问；缺点是需要自定义域名。