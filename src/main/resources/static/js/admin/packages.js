deletePackage()
function deletePackage() {
	var btnDelete = document.querySelectorAll(".btnDelete")

	for (let i = 0; i < btnDelete.length; i++) {
		btnDelete[i].addEventListener("click", () => {
			var newRow = document.querySelectorAll(".newRow")
			var id = btnDelete[i].getAttribute("id")
			var row = btnDelete[i].parentNode.parentNode

			swal({
				text: `Bạn chắc muốn xóa đơn hàng này ra khỏi nhà kho?`,
				buttons: ["Hủy", 'Đồng ý'],
				dangerMode: true,
			})
				.then((okay) => {
					if (okay) {
						$.ajax({
							url: "/delete-package",
							type: "post",
							data: {
								id: id,
							},
							success: function(response) {
								if (response == 'success') {
									row.remove()
									if (newRow[i]) {
										newRow[i].remove()
									}
									swal("", "Xóa đơn hàng thành công !", "success")
								} else {
									swal("", "Không thể xóa đơn hàng này !", "warning")
								}
							}
						})
					}
				})
		})
	}
}

var statusOrder = document.querySelectorAll(".statusOrder")

for (let i = 0; i < statusOrder.length; i++) {
	btnActionChange(statusOrder[i], i)
}

function btnActionChange(statusOrder, index) {
	var btnAccept = document.querySelectorAll(".btnAccept")
	var btnCancel = document.querySelectorAll(".btnCancel")
	var btnRestore = document.querySelectorAll(".btnRestore")
	var status = statusOrder.innerHTML

	if (status == 'Bị hủy' || status == 'Hoàn thành') {
		btnAccept[index].style.display = "none"
		btnCancel[index].style.display = "none"
		btnRestore[index].style.display = ""
	}
	else if (status == 'Chờ xác nhận') {
		btnAccept[index].style.display = ""
		btnCancel[index].style.display = ""
		btnRestore[index].style.display = "none"
	}
	else {
		btnAccept[index].style.display = ""
		btnCancel[index].style.display = ""
		btnRestore[index].style.display = ""
	}
}

acceptPackage()
function acceptPackage() {
	var btnAccept = document.querySelectorAll(".btnAccept")
	var statusOrder = document.querySelectorAll(".statusOrder")

	for (let i = 0; i < btnAccept.length; i++) {
		btnAccept[i].addEventListener("click", () => {
			var id = btnAccept[i].getAttribute("id")

			swal({
				text: `Bạn chắc muốn duyệt đơn hàng này?`,
				buttons: ["Hủy", 'Đồng ý'],
				dangerMode: true,
			})
				.then((okay) => {
					if (okay) {
						$.ajax({
							url: "/accept-package",
							type: "post",
							data: {
								id: id,
							},
							success: function(response) {
								if (response == 'success') {
									var status = statusOrder[i].innerHTML
									if (status == 'Chờ xác nhận') {
										statusOrder[i].innerHTML = 'Đã xác nhận'
									}
									else if (status == 'Đã xác nhận') {
										statusOrder[i].innerHTML = 'Đang vận chuyển'
									}
									else if (status == 'Đang vận chuyển') {
										statusOrder[i].innerHTML = 'Hoàn thành'
									}
									btnActionChange(statusOrder[i], i)
									swal("", "Duyệt đơn hàng thành công !", "success")
								} else {
									swal("", "Không thể duyệt đơn hàng này !", "warning")
								}
							}
						})
					}
				})
		})
	}
}

cancelPackage()
function cancelPackage() {
	var btnCancel = document.querySelectorAll(".btnCancel")
	var statusOrder = document.querySelectorAll(".statusOrder")

	for (let i = 0; i < btnCancel.length; i++) {
		btnCancel[i].addEventListener("click", () => {
			var id = btnCancel[i].getAttribute("id")

			swal({
				text: `Bạn chắc muốn hủy đơn hàng này?`,
				buttons: ["Hủy", 'Đồng ý'],
				dangerMode: true,
			})
				.then((okay) => {
					if (okay) {
						$.ajax({
							url: "/cancel-package",
							type: "post",
							data: {
								id: id,
							},
							success: function(response) {
								if (response == 'success') {
									statusOrder[i].innerHTML = 'Bị hủy'
									btnActionChange(statusOrder[i], i)
									swal("", "Hủy đơn hàng thành công !", "success")
								} else {
									swal("", "Không thể hủy đơn hàng này !", "warning")
								}
							}
						})
					}
				})
		})
	}
}

restorePackage()
function restorePackage() {
	var btnRestore = document.querySelectorAll(".btnRestore")
	var statusOrder = document.querySelectorAll(".statusOrder")

	for (let i = 0; i < btnRestore.length; i++) {
		btnRestore[i].addEventListener("click", () => {
			var id = btnRestore[i].getAttribute("id")

			swal({
				text: `Bạn chắc muốn khôi phục đơn hàng này?`,
				buttons: ["Hủy", 'Đồng ý'],
				dangerMode: true,
			})
				.then((okay) => {
					if (okay) {
						$.ajax({
							url: "/restore-package",
							type: "post",
							data: {
								id: id,
							},
							success: function(response) {
								if (response == 'success') {
									var status = statusOrder[i].innerHTML
									if (status == 'Hoàn thành') {
										statusOrder[i].innerHTML = 'Đang vận chuyển'
									}
									else if (status == 'Đang vận chuyển') {
										statusOrder[i].innerHTML = 'Đã xác nhận'
									}
									else if (status == 'Đã xác nhận') {
										statusOrder[i].innerHTML = 'Chờ xác nhận'
									}
									else if (status == 'Bị hủy') {
										statusOrder[i].innerHTML = 'Chờ xác nhận'
									}
									btnActionChange(statusOrder[i], i)
									swal("", "Khôi phục hàng thành công !", "success")
								} else {
									swal("", "Không thể hủy đơn hàng này !", "warning")
								}
							}
						})
					}
				})
		})
	}
}

seeDetailOrder()
function seeDetailOrder() {
	var btnDetail = document.querySelectorAll(".btnDetail")
	var btnReturn = document.querySelectorAll(".btnReturn")

	for (let i = 0; i < btnDetail.length; i++) {
		btnDetail[i].addEventListener("click", () => {
			var flag = true
			var id = btnDetail[i].getAttribute("id")

			$.ajax({
				url: "/detail-package",
				type: "get",
				data: {
					id: id,
				},
				success: function(response) {
					btnDetail[i].style.display = "none"
					btnReturn[i].style.display = "inline"
					var newRow = document.createElement('tr');
					newRow.setAttribute("class", "newRow")
					var newColumn = document.createElement('td');
					newColumn.setAttribute("colspan", "6")
					newRow.appendChild(newColumn)
					var currentRow = btnDetail[i].parentNode.parentNode;
					currentRow.parentNode.insertBefore(newRow, currentRow.nextSibling);
					newColumn.innerHTML = response

					if (flag) {
						btnReturn[i].addEventListener("click", () => {
							flag = false
							btnDetail[i].style.display = ""
							btnReturn[i].style.display = "none"
							newRow.remove()
						})
					}
				}
			})
		})
	}
}