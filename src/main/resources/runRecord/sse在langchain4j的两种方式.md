## 1.LangChain4j + React
Reactor是只响应式编程，LangChain4j提供了响应式编程依赖包，可以直接把AI返回的内容封装为更通用的Flux响应式对象。可以把Flux想象成一个数据流，有了这个对象之后，上游发来一块数据，下游就能处理一块数据。

这种方案的优点是与前端集成更方便，通过Flux对象可以很容易地将流式内容返回给前端。缺点是需要引入额外的依赖:
```xml
<dependency>
  <groupId>dev.langchain4j</groupId>
  <artifactId>langchain4j-reactor</artifactId>
  <version>1.1.0-beta7</version>
</dependency>

```
## 2、TokenStream(了解即可)
这是LangChain4j的原生实现方式，好处是提供了更多高级回调，比如工具调用完成回调(onToolExecute)、工具调用内容实时响应。但缺点是使用起来相对复杂，而且要返回前端时还需要用Flux包装一层。

