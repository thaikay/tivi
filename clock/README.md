# ⏰ ThaiTV Digital Clock

Một ứng dụng web hiển thị đồng hồ số với hỗ trợ nhiều múi giờ. Thiết kế tối giản, responsive và dễ sử dụng.

## ✨ Tính Năng

- 🌍 **Hỗ trợ 20+ múi giờ** - Từ Á châu đến Âu châu, Mỹ và Úc
- 📱 **Responsive Design** - Hoạt động trên Desktop, Tablet, Mobile
- 🌙 **Chế độ Tối/Sáng** - Chuyển đổi dễ dàng giữa hai chế độ
- ➕ **Thêm/Xóa Múi Giờ** - Quản lý động theo nhu cầu
- 💾 **Lưu Cài Đặt** - Tự động lưu vào LocalStorage
- ⏱️ **Cập Nhật Thực Thời** - Cập nhật mỗi giây
- 🎨 **Giao Diện Hiện Đại** - Material Design UI
- ⚡ **Nhẹ & Nhanh** - ~15KB, không cần backend

## 🚀 Cách Sử Dụng

### 1. Mở Ứng Dụng

#### Cách 1: Mở trực tiếp file HTML
```bash
# Windows
start clock/index.html

# macOS
open clock/index.html

# Linux
xdg-open clock/index.html
```

#### Cách 2: Sử dụng HTTP Server
```bash
# Python 3
python3 -m http.server 8000

# Python 2
python -m SimpleHTTPServer 8000

# Node.js (cần cài http-server)
npx http-server

# PHP
php -S localhost:8000
```

Sau đó truy cập: **http://localhost:8000/clock/**

### 2. Tương Tác với Ứng Dụng

#### Thêm Múi Giờ
1. Chọn múi giờ từ dropdown "Thêm Múi Giờ"
2. Nhấn nút "➕ Thêm"
3. Đồng hồ mới sẽ xuất hiện

#### Xóa Múi Giờ
- Nhấn nút "✕" ở góc trên phải của thẻ đồng hồ

#### Chuyển Đổi Chế Độ
- Nhấn 🌙 (hoặc ☀️) ở góc trên phải của header

#### Phím Tắt
- **Alt + T** - Chuyển đổi chế độ tối/sáng
- **Esc** - Xóa lựa chọn dropdown
- **Enter** - Thêm múi giờ đã chọn

## 🌍 Múi Giờ Được Hỗ Trợ

### Á Châu
- 🇻🇳 Việt Nam (Asia/Ho_Chi_Minh) - UTC+7
- 🇹🇭 Thái Lan (Asia/Bangkok) - UTC+7
- 🇯🇵 Nhật Bản (Asia/Tokyo) - UTC+9
- 🇨🇳 Trung Quốc (Asia/Shanghai) - UTC+8
- 🇭🇰 Hồng Kông (Asia/Hong_Kong) - UTC+8
- 🇸🇬 Singapore (Asia/Singapore) - UTC+8
- 🇰🇷 Hàn Quốc (Asia/Seoul) - UTC+9
- 🇮🇳 Ấn Độ (Asia/Kolkata) - UTC+5:30
- 🇦🇪 Các Tiểu Vương Quốc (Asia/Dubai) - UTC+4

### Âu Châu
- 🇬🇧 Anh (Europe/London) - UTC+0/+1
- 🇫🇷 Pháp (Europe/Paris) - UTC+1/+2
- 🇩🇪 Đức (Europe/Berlin) - UTC+1/+2
- 🇷🇺 Nga (Europe/Moscow) - UTC+3

### Mỹ
- 🇺🇸 New York (America/New_York) - UTC-5/-4
- 🇺🇸 Chicago (America/Chicago) - UTC-6/-5
- 🇺🇸 Denver (America/Denver) - UTC-7/-6
- 🇺🇸 Los Angeles (America/Los_Angeles) - UTC-8/-7
- 🇺🇸 Anchorage (America/Anchorage) - UTC-9/-8

### Khác
- 🇦🇺 Úc (Australia/Sydney) - UTC+10/+11
- 🇳🇿 New Zealand (Pacific/Auckland) - UTC+12/+13
- ⏰ UTC (Giờ Quốc tế) - UTC+0

## 💾 Dữ Liệu Lưu Trữ

Ứng dụng sử dụng **LocalStorage** để lưu:
- **Chế độ (Light/Dark)** - Key: `thaitv-clock-theme`
- **Danh sách múi giờ** - Key: `thaitv-clock-timezones`

Dữ liệu được lưu trữ cục bộ trong trình duyệt, không gửi đến server.

## 📁 Cấu Trúc Tệp

```
clock/
├── index.html          # Cấu trúc HTML
├── styles.css          # Styling & Responsive Design
├── script.js           # Logic JavaScript
└── README.md           # Tài liệu này
```

## 🔧 Công Nghệ Sử Dụng

- **HTML5** - Cấu trúc
- **CSS3** - Styling (Flexbox, Grid, Media Queries)
- **JavaScript (Vanilla)** - Logic (Intl API, LocalStorage)
- **Intl.DateTimeFormat** - Xử lý múi giờ

## 🎨 Thiết Kế

### Chế Độ Sáng
- Nền trắng, văn bản đen
- Màu accent xanh dương

### Chế Độ Tối
- Nền tối, văn bản trắng
- Màu accent xanh sáng

### Responsive Breakpoints
- **Desktop** (> 768px) - Grid 4 cột
- **Tablet** (481px - 768px) - Grid 2 cột
- **Mobile** (< 480px) - Grid 1 cột
- **Small Mobile** (< 320px) - Tối ưu hóa

## ⚙️ Cấu Hình

### Thêm Múi Giờ Mới

Chỉnh sửa `script.js`, thêm vào object `timezoneInfo`:

```javascript
timezoneInfo = {
    // ... existing timezones ...
    'Asia/Bangkok': { name: '🇹🇭 Thái Lan', flag: '🇹🇭' },
    // Thêm múi giờ mới
    'Your/Timezone': { name: '🌍 Nước Của Bạn', flag: '🚩' }
};
```

### Thay Đổi Múi Giờ Mặc Định

Chỉnh sửa hàm `loadClocks()` trong `script.js`:

```javascript
const defaultClocks = [
    'Asia/Ho_Chi_Minh',  // Thay đổi theo ý muốn
    'Asia/Bangkok',
    'Asia/Tokyo',
    'America/New_York'
];
```

## 🐛 Xử Lý Lỗi

### Múi Giờ không hợp lệ
- Kiểm tra tên múi giờ có đúng không
- Xem danh sách [IANA Time Zone Database](https://www.iana.org/time-zones)

### Đồng hồ không cập nhật
- Làm tải lại trang (F5 hoặc Ctrl+R)
- Xóa cache trình duyệt
- Kiểm tra console (F12) để xem lỗi

### LocalStorage không hoạt động
- Kiểm tra bộ nhớ trình duyệt
- Vô hiệu hóa chế độ riêng tư nếu cần
- Thử trình duyệt khác

## 🌐 Hỗ Trợ Trình Duyệt

- ✅ Chrome/Edge (88+)
- ✅ Firefox (85+)
- ✅ Safari (14+)
- ✅ Opera (74+)
- ⚠️ IE 11 (hạn chế)

## 📚 Tài Nguyên

- [Intl API Documentation](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Intl)
- [IANA Time Zone Database](https://www.iana.org/time-zones)
- [MDN Web Docs](https://developer.mozilla.org/en-US/)
- [CSS Grid Guide](https://developer.mozilla.org/en-US/docs/Web/CSS/CSS_Grid_Layout)
- [Responsive Design](https://developer.mozilla.org/en-US/docs/Learn/CSS/CSS_layout/Responsive_Design)

## 📝 Giấy Phép

Dự án này được cấp phép dưới **MIT License**.

## 👨‍💻 Phát Triển

Đóng góp được chào đón! Vui lòng:

1. Fork repository
2. Tạo branch feature (`git checkout -b feature/AmazingFeature`)
3. Commit (`git commit -m 'Add AmazingFeature'`)
4. Push (`git push origin feature/AmazingFeature`)
5. Mở Pull Request

## 🔮 Tính Năng Trong Tương Lai

- [ ] Đồng hồ analog (kim)
- [ ] Báo thức (Alarm)
- [ ] Stopwatch / Timer
- [ ] Chuyển đổi múi giờ
- [ ] Export/Import cấu hình
- [ ] PWA (Progressive Web App)
- [ ] Widget desktop
- [ ] Support thêm ngôn ngữ

## 📞 Liên Hệ

- GitHub: [@thaikay](https://github.com/thaikay)
- Repository: [thaikay/tivi](https://github.com/thaikay/tivi)

## 🎉 Cảm Ơn

Cảm ơn vì sử dụng **ThaiTV Digital Clock**!

---

**Được phát triển với ❤️ bởi ThaiKay**
