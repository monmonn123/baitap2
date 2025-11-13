# Bài tập 2.1

Đây là một ứng dụng Android đơn giản thể hiện cách thay đổi hình nền của màn hình một cách ngẫu nhiên.

## Tính năng

- Khi khởi động, ứng dụng sẽ hiển thị một hình nền ngẫu nhiên từ một danh sách có sẵn.
- Người dùng có thể thay đổi hình nền bằng cách gạt công tắc "Đổi nền". Mỗi lần gạt, một hình nền ngẫu nhiên mới sẽ được chọn và hiển thị.
- Ứng dụng sử dụng các hình ảnh: `troi.jpg`, `doraemon.jpg`, và `anime.jpg`.

## Cách hoạt động

- `MainActivity.java` chứa logic chính để xử lý việc thay đổi hình nền.
- Một mảng `backgrounds` lưu trữ ID của các tài nguyên hình ảnh.
- Khi công tắc được gạt, một hình ảnh ngẫu nhiên từ mảng sẽ được chọn và đặt làm nền cho `ConstraintLayout` chính.
- `activity_main.xml` định nghĩa giao diện người dùng, bao gồm `ConstraintLayout` và `SwitchCompat`.

## Cách chạy dự án

1. Mở dự án bằng Android Studio.
2. Build và chạy ứng dụng trên máy ảo hoặc thiết bị Android thật.
3. Gạt công tắc để xem sự thay đổi của hình nền.

![ảnh demo](troi.png)
![ảnh demo](anime.png)
![ảnh demo](doraemon.png)