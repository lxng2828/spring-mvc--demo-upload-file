## 📚 Tổng quan: RFC 8089 – *The "file" URI Scheme*

* RFC 8089 là tiêu chuẩn chính thức xác định cách sử dụng `file:` URI để trỏ tới tài nguyên cục bộ (local file).
* Dạng chung của một URI `file:` là:

```
file://<host>/<path>
```

---

## 🔍 Các thành phần

| Thành phần | Ý nghĩa                                                                   |
| ---------- | ------------------------------------------------------------------------- |
| `file:`    | Scheme (giao thức)                                                        |
| `//`       | Bắt đầu phần *authority* (host)                                           |
| `<host>`   | Tùy chọn. Nếu bỏ trống thì là localhost (`localhost` hoặc trống đều được) |
| `<path>`   | Đường dẫn tuyệt đối đến file trên hệ thống                                |

---

## 💡 Ví dụ minh hoạ

### Trên **Windows**:

| Mô tả                              | URI hợp lệ                       |
| ---------------------------------- | -------------------------------- |
| Ảnh trong `C:\Users\PETER\img.jpg` | `file:///C:/Users/PETER/img.jpg` |

* Ba dấu `/` vì:

  * `file://` → chỉ định scheme và bắt đầu host (bỏ trống hoặc localhost)
  * `/C:/...` → đường dẫn tuyệt đối trên hệ thống

⚠️ Nếu viết `file://C:/Users/...` → **SAI**, vì `C:` sẽ được hiểu là hostname, không phải drive letter.

---

### Trên **Linux**:

| Mô tả                      | URI hợp lệ                 |
| -------------------------- | -------------------------- |
| File `/home/user/file.txt` | `file:/home/user/file.txt` |

* Chỉ cần 1 dấu `/` sau `file:` vì không có host, và đường dẫn `/...` đã là tuyệt đối.
* Có thể viết đầy đủ: `file:///home/user/file.txt` (vẫn đúng, nhưng không cần thiết).

---

## 🔒 Về `host` trong `file://<host>/path`

* RFC 8089 cho phép chỉ định `host` nếu truy cập file qua mạng nội bộ (rất hiếm).
* Ví dụ: `file://server/share/file.txt`
* Nhưng hầu hết trường hợp dùng **host trống hoặc `localhost`**:

  * `file:///path/to/file`
  * `file://localhost/path/to/file` (tương đương)

---

## 🚫 Các sai lầm thường gặp

| Sai lầm                                        | Lý do                                                           |
| ---------------------------------------------- | --------------------------------------------------------------- |
| `file://C:/Users/...` (2 dấu `/`)              | Sai, `C:` bị hiểu nhầm là host                                  |
| `file:/C:/Users/...`                           | Sai cú pháp RFC, cần `file:///C:/...` để tách rõ host và path   |
| Quên dấu `/` cuối trong `addResourceLocations` | Spring sẽ không trỏ đúng thư mục gốc để mapping static resource |

---

## ✅ Tổng kết cú pháp chuẩn

```text
file://[host]/absolute/path
```

* **Windows:** `file:///C:/path/to/file`
* **Linux/macOS:** `file:/home/user/file.txt`
