### Kotlin Style Guide
[Google의 Kotlin Coding Style Guide](https://developer.android.com/kotlin/style-guide)를 따릅니다.

### Class Layout
다음과 같은 순서 클래스를 구성합니다.
- Property 선언과 초기화 블럭(intializer blocks)
- 추가적인 생성자
- 메소드 정의
- 컴패니언 오브젝트(Companion object)

### Naming Rule
#### Package
- 패키지의 이름은 항상 소문자로 하고, 밑줄을 사용하지 않습니다.
- 두 개 이상의 단어를 한 번에 사용하는 것을 금지합니다.

#### Class/Object
- Pascal Case
```
open class SampleName { /* ... */ }
object MoreSampleName : SampleName() { /* ... */ }
```

#### Function/Parameter/Variable
- Camel Case
```
val initList = mutableList<SampleData>()
fun getList: List<SampleData>() { /* ... */ }
```
#### Constant
- Upper Snake Case
- 상수는 companion objet에 넣어 보관합니다.
```
companion object {
    const val MAX_COUNT = 8
}
```

### Formatting
#### Indenting
Tab 키를 써서 Indenting 합니다.

#### 중괄호
괄호 뒤에 한 칸을 띄우고 사용합니다.
```
if (elements != null) {
    for (element in elements) {
        // ...
    }
}
```

#### 괄호
- Control문(if/while/for)
    - 한 칸 띄어씁니다.
- 생성자/Method
    - 붙여씁니다.
```
if (isSpacing == true) { /*...*/ }
fun isSpacing() { /*...*/ }
```

#### Colon(:)
- 변수의 타입/함수 리턴 타입 결정
    - 콜론을 앞에 붙여씁니다. 
- 상속받은 클래스/인터페이스 구분
    - 한 칸 띄어씁니다.
```
fun isSpacing(): Boolean { /*...*/ }
class MainActivity : AppCompatActivity()
```
