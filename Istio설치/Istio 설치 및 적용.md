# Istio 설치 및 적용

이론 : [Istio 이론 정리](https://www.notion.so/Docker-Kubernetes-c9168f4ed5f243da84b19111cd59210b)



### Istioctl 최신 버전 다운로드 및 설치

```bash
$ curl -L https://istio.io/downloadIstio | sh -
$ cd istio-최신버전
$ export PATH=$PWD/bin:$PATH
$ istioctl
```

![image-20210224103122324](C:\Users\INNOGRID\AppData\Roaming\Typora\typora-user-images\image-20210224103122324.png)

```bash
$ istioctl manifest apply --set profile=demo
```

-> demo로 설치하면 모든 걸 알아서 설치해줌



### 프로젝트에 istio 적용

```bash
$ istioctl profile list 
```

-> 설치한거 확인

```bash
$ istioctl manifest apply --set profile=demo
```

->Istio, Istiod, Ingress, Egress, Addons 설치됨



### Istio Integrations 설치하기

> 최근 버전 업데이트 하면서 각자 설치하는 방법들로 바뀜

Grafana:

```bash
$ kubectl apply -f https://raw.githubusercontent.com/istio/istio/release-1.9/samples/addons/grafana.yaml
```

Jaeger:

```bash
$ kubectl apply -f https://raw.githubusercontent.com/istio/istio/release-1.9/samples/addons/jaeger.yaml
```

Kiali:

```bash
$ kubectl apply -f https://raw.githubusercontent.com/istio/istio/release-1.9/samples/addons/kiali.yaml
```

Prometheus:

```bash
$ kubectl apply -f https://raw.githubusercontent.com/istio/istio/release-1.9/samples/addons/prometheus.yaml
```

Zipkin:

```bash
$ kubectl apply -f https://raw.githubusercontent.com/istio/istio/release-1.9/samples/addons/extras/zipkin.yaml
```

**추후 kiali 대시보드로 모든 integerations 모니터링 할 것이기 때문에 모두 설치**



```bash
$ kubectl label namespace default istio-injection=enabled
```

->namespace 설정

```bash
$ kubectl apply -f samples/bookinfo/platform/kube/bookinfo.yaml
```

-> book 예제에 istio apply

```bash
$ kubectl get pod
```

->pod 조회 시 details, productpage, ratings, review 3가지 버전 출력



### 게이트웨이 설치와 관찰

```bash
$ kubectl apply -f samples/bookinfo/networking/bookinfo-gateway.yaml
```

-> 게이트웨이 추가 설치

```bash
$ kubectl get gateways
```

->게이트웨이 생성 확인

```bash
$ kubectl get svc -n istio-system -l istio=ingressgateway --all-namespaces
```

->80번 포트에 연결된 포트번호 확인, localhost:포트번호/productpage로 접속



### Kiali 대시보드 연결

```bash
$ kubectl get svc -n istio-system
```

->을 통해서 확인하면 kiali는 타입이 ClusterIP로 되어잇음

![image-20210224154329440](C:\Users\INNOGRID\AppData\Roaming\Typora\typora-user-images\image-20210224154329440.png)

포트번호 지정을 위해 

```bash
$ kubectl edit svc kiali -n istio-system
```

![image-20210224154548618](C:\Users\INNOGRID\AppData\Roaming\Typora\typora-user-images\image-20210224154548618.png)

빨간 부분을 LoadBalancer로 변경하면 자동으로 포트지정됨

아이피주소:해당 포트 번호/kiali로 접속하면 dashboard 접속됨.