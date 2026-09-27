# Android 界面布局实验报告

## 一、实验目的

- 掌握 Android 中 `LinearLayout`、`TableLayout`、`ConstraintLayout` 三种常用布局的用法
- 掌握 Jetpack Compose 的声明式 UI 写法与状态管理

---

## 二、实验环境

| 项目 | 内容 |
|---|---|
| 开发工具 | Android Studio |
| 开发语言 | Kotlin + XML |
| UI 框架 | 传统 View 体系 + Jetpack Compose |
| 运行平台 | Android 模拟器  |

---

## 三、实验内容

### 实验 1：利用线性布局实现四行四列界面(linear_task)

#### 1. 实现思路

使用嵌套 LinearLayout：

- 外层：垂直 `LinearLayout`，包含 4 行
- 内层：水平 `LinearLayout`，每行包含 4 个 `TextView`
- 每个 `TextView` 宽度 `0dp` + `layout_weight="1"`，平均分宽度
- 每行高度 `0dp` + `layout_weight="1"`，平均分高度

#### 2. 核心代码

```xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="5dp"
    android:background="@color/black">
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="30dp"
        android:orientation="horizontal">
        <TextView
            android:layout_width="0dp"
            android:layout_height="match_parent"
            android:layout_weight="1"
            android:text="@string/one1"
            android:textSize="15sp"
            android:gravity="center"
            android:background="@drawable/border"
            android:layout_margin="2dp">

        </TextView>
        <!--以下同理-->
```

#### 4. 效果

<img width="462" height="296" alt="image" src="https://github.com/user-attachments/assets/8a4b026b-8bbe-4426-9999-32552f5c6b2e" />


---

### 实验 2：利用表格布局实现界面(table_task)

#### 1. 实现思路

使用 `TableLayout` + `TableRow`：

- `TableLayout` 作为根布局
- 每个 `TableRow` 代表一行
- 每行内放多个 `TextView`，代表各列

#### 2. 核心代码

```xml
<TableLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:stretchColumns="*">

    <TableRow>
        <TextView android:text="One,One" android:gravity="center" />
        <TextView android:text="One,Two" android:gravity="center" />
        <TextView android:text="One,Three" android:gravity="center" />
        <TextView android:text="One,Four" android:gravity="center" />
    </TableRow>

    <!-- 其余 3 行同理 -->
</TableLayout>
```

#### 3.效果

<img width="432" height="572" alt="image" src="https://github.com/user-attachments/assets/36777b2b-e53c-4819-b324-70709cf0ef38" />


---

### 实验 3：利用 ConstraintLayout 实现界面(constraint_task1)

#### 1. 实现思路

- 每个控件至少写一个水平约束 + 一个垂直约束
- 约束格式：`app:layout_constraint[自己的边]_to[对方的边]Of="对方id"`
- 多个控件并排时，使用链（Chain）实现均匀分布

#### 2. 水平链示例

```xml
<TextView
        android:id="@+id/n7"
        android:layout_width="0dp"
        android:layout_height="40dp"
        android:layout_marginTop="40dp"
        android:text="7"
        android:textSize="20sp"
        android:gravity="center"
        android:layout_marginStart="20dp"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@id/output"
        android:background="#808080"
        app:layout_constraintEnd_toStartOf="@id/n8"
        />

    <TextView
        android:id="@+id/n8"
        android:layout_width="0dp"
        android:layout_height="40dp"
        android:layout_marginTop="40dp"
        android:text="8"
        android:textSize="20sp"
        android:gravity="center"
        android:layout_marginStart="20dp"
        app:layout_constraintStart_toEndOf="@id/n7"
        app:layout_constraintTop_toBottomOf="@id/output"
        android:background="#808080"
        app:layout_constraintEnd_toStartOf="@id/n9"
        />

    <TextView
        android:id="@+id/n9"
        android:layout_width="0dp"
        android:layout_height="40dp"
        android:layout_marginTop="40dp"
        android:text="9"
        android:textSize="20sp"
        android:gravity="center"
        android:layout_marginStart="20dp"
        app:layout_constraintStart_toEndOf="@id/n8"
        app:layout_constraintTop_toBottomOf="@id/output"
        android:background="#808080"
        app:layout_constraintEnd_toStartOf="@id/add"
        />

    <TextView
        android:id="@+id/add"
        android:layout_width="0dp"
        android:layout_height="40dp"
        android:layout_marginTop="40dp"
        android:text="+"
        android:textSize="20sp"
        android:gravity="center"
        android:layout_marginStart="20dp"
        app:layout_constraintStart_toEndOf="@id/n9"
        app:layout_constraintTop_toBottomOf="@id/output"
        android:background="#808080"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginEnd="20dp"
        />
```

#### 4. 效果

<img width="366" height="664" alt="image" src="https://github.com/user-attachments/assets/55db30da-e5f8-490c-972a-5b31b09e947f" />


---

### 实验 4：利用 ConstraintLayout 实现带图片的界面(contraint_task2)

#### 1. 实现思路

- 图片资源放入 `res/drawable`
- 使用 `ImageView` 显示，`android:src="@drawable/图片名"` 引用
- 用packed链把图片和文字定位

#### 2. 图片 + 文字整体居中

```xml
<androidx.constraintlayout.widget.ConstraintLayout
    android:id="@+id/box1"
    android:layout_width="0dp"
    android:layout_height="100dp"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintEnd_toStartOf="@id/box2"
    app:layout_constraintTop_toTopOf="parent">

    <ImageView
        android:id="@+id/iv1"
        android:layout_width="50dp"
        android:layout_height="50dp"
        android:src="@drawable/space_station_icon"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintBottom_toTopOf="@id/tv1"
        app:layout_constraintVertical_chainStyle="packed">
    </ImageView>

    <TextView
        android:id="@+id/tv1"
        android:layout_height="20dp"
        android:layout_width="match_parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintTop_toBottomOf="@id/iv1"
        android:gravity="center"
        android:text="@string/space_station">

    </TextView>

</androidx.constraintlayout.widget.ConstraintLayout>
```
### 3. 效果

<img width="454" height="816" alt="image" src="https://github.com/user-attachments/assets/c692b7f4-63a6-4c19-9dcf-151a6eed5af9" />


---

### 实验 5：使用 Jetpack Compose 实现任务界面（Page 7）

#### 1. 功能要求

- 初始状态：3 项任务，完成 1 项
- 添加任务：新增“复习 LazyColumn”
- 状态更新：勾选任务后，完成数和删除线同步变化

#### 2. 核心机制

> 状态变了，界面自动重组。

- 状态用 `mutableStateOf` 和 `mutableStateListOf` 管理
- 修改状态后，Compose 自动重新渲染相关 UI
- 不需要手动查找控件、手动更新文字

#### 3. 数据结构

```kotlin
data class Task(
    val id: Int,
    val title: String,
    val done: Boolean = false
)
```


#### 4. 添加任务

```kotlin
Button(onClick = {
    if (input.isNotBlank()) {
        tasks.add(Task(tasks.size + 1, input))
        input = ""
    }
}) {
    Text("添加")
}
```

#### 5. 勾选任务

```kotlin
onCheckedChange = { checked ->
    val index = tasks.indexOf(task)
    tasks[index] = task.copy(done = checked)
}
```

- 用 `copy` 生成新对象，替换列表中旧对象
- `mutableStateListOf` 检测到变化，触发界面刷新

#### 6. 删除任务

```kotlin
onDelete = {
    tasks.remove(task)
}
```

#### 7. 样式随状态变化

```kotlin
Text(
    text = task.title,
    textDecoration = if (task.done) TextDecoration.LineThrough else TextDecoration.None,
    color = if (task.done) Color.Gray else Color.Black
)
```

- 已完成：灰色 + 删除线
- 未完成：黑色 + 无删除线

#### 8. 效果

<img width="630" height="1146" alt="image" src="https://github.com/user-attachments/assets/66921ce1-68a1-41b5-b9cf-e9cbd421d213" />


---


## 四、结论

通过本次实验：

- 掌握了三种传统布局的用法和适用场景
- 理解了 `layout_weight`、链、约束的核心机制
- 掌握了 Compose 的声明式 UI 和状态管理
