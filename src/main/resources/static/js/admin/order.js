formProduct()
function formProduct() {
	var formProduct = document.querySelector(".form-product")
	createForm()
	function createForm() {
		var createForms = document.querySelectorAll(".create-form")
		var deleteForms = document.querySelectorAll(".delete-form")

		createForms.forEach((e, i) => {
			e.addEventListener("click", () => {
				if (validateFormP()) {
					createForms[i].style.display = 'none'
					if (deleteForms[i]) {
						deleteForms[i].style.display = 'flex'
					}
					var formAddProduct = document.createElement('div');
					formAddProduct.setAttribute('class', 'form-addProduct mb-2');
					formProduct.appendChild(formAddProduct)

					formAddProduct.innerHTML = `
					<div class="uploadImage">
						<input type="file" name="fileImage" class="fileImage" multiple>
						<label class="lbFileImage">
							<i class="fa-solid fa-camera"></i>
							<img class="showImage">
						</label>
						<div class="message msProduct"></div>
					</div>
					
					<div class="form-right">
						<div class="form-group mx-2">
							<input type="text" class="form-control nameP" placeholder="Nhập tên sản phẩm">
							<div class="message msNameProduct"></div>
						</div>
						<div class="form-group mx-2">
							<input type="number" class="form-control massP" placeholder="Nhập khối lượng">
							<div class="message msMass"></div>
						</div>
						<div class="form-group mx-2">
							<input type="number" class="form-control quantityP" placeholder="Nhập số lượng">
							<div class="message msQuantity"></div>
						</div>
					</div>
					
					<div class="create-form"><i class="fa-solid fa-plus"></i></div>
					<div class="delete-form"><i class="fa-solid fa-xmark"></i></div>
				`
					createForm()
					deleteForm()
					uploadFileImage()
				}
			})
		})
	}

	deleteForm()
	function deleteForm() {
		var deleteForms = document.querySelectorAll(".delete-form")
		deleteForms.forEach((e) => {
			e.addEventListener("click", (e) => {
				var formAddP = e.target
				if (formAddP.classList.contains('fa-xmark')) {
					formAddP = e.target.parentNode.parentNode
				} else {
					formAddP = e.target.parentNode
				}
				formAddP.remove()
			})
		})
	}

	uploadFileImage()
	function uploadFileImage() {
		var lbFileImage = document.querySelectorAll(".lbFileImage");
		var fileImage = document.querySelectorAll(".fileImage");
		var showImage = document.querySelectorAll(".showImage");
		var faCamera = document.querySelectorAll(".fa-camera");
		var imageName = document.querySelectorAll(".showImage")

		lbFileImage.forEach((e, i) => {
			e.addEventListener("click", () => {
				fileImage[i].click()
			})
		})

		fileImage.forEach((e, i) => {
			e.addEventListener("change", function() {
				if (fileImage[i].files && fileImage[i].files[0]) {
					const checkImage = /(\.jpg|\.jpeg|\.png|\.gif)$/i;
					const maxSize = 20 * 1024 * 1024; // 20 MB

					if (fileImage[i].files[0].size <= maxSize && checkImage.test(fileImage[i].files[0].name)) {
						showImage[i].style.display = "block"
						showImage[i].src = URL.createObjectURL(fileImage[i].files[0]);
						faCamera[i].style.display = "none"
						imageName[i].setAttribute("imageName", fileImage[i].files[0].name)
					} else {
						message('Vui lòng chọn một hình ảnh có định dạng hợp lệ và kích thước không quá 20 MB.', "warming");
						fileImage[i].value = ''
					}
				}
			})
		})
	}

	function validateFormP() {
		var check = true
		var nameP = document.querySelectorAll(".nameP")
		var massP = document.querySelectorAll(".massP")
		var quantityP = document.querySelectorAll(".quantityP")

		for (let i = 0; i < nameP.length; i++) {
			if (nameP[i].value == '') {
				nameP[i].style = 'box-shadow: 0 0 0 2px rgba(255,79,44,0.4);'
				check = false
			}
			if (massP[i].value == '') {
				massP[i].style = 'box-shadow: 0 0 0 2px rgba(255,79,44,0.4);'
				check = false
			}
			if (quantityP[i].value == '') {
				quantityP[i].style = 'box-shadow: 0 0 0 2px rgba(255,79,44,0.4);'
				check = false
			}

			changeColorForm(nameP[i])
			changeColorForm(massP[i])
			changeColorForm(quantityP[i])
		}

		function changeColorForm(element) {
			element.addEventListener("input", () => {
				if (element.value != '') {
					element.style = 'box-shadow:  0 0 0 2px rgba(13, 110, 253, .25)'
				} else {
					element.style = 'box-shadow: 0 0 0 2px rgba(255,79,44,0.4);'
				}

				element.addEventListener("blur", () => {
					if (element.style.boxShadow == 'rgba(13, 110, 253, 0.25) 0px 0px 0px 2px') {
						element.style = 'box-shadow:  none'
					}
				})
			})


			element.addEventListener("focus", () => {
				if (element.style.boxShadow != 'rgba(255, 79, 44, 0.4) 0px 0px 0px 2px') {
					element.style = 'box-shadow:  0 0 0 2px rgba(13, 110, 253, .25)'
				}
			})
		}
		return check
	}
}

function validateForm() {
	var check = true
	var formControl = document.querySelectorAll(".form-control")

	for (let i = 0; i < formControl.length; i++) {
		if (formControl[i].value == '') {
			formControl[i].style = 'box-shadow: 0 0 0 2px rgba(255,79,44,0.4);'
			check = false
		}

		formControl[i].addEventListener("input", () => {
			if (formControl[i].value != '') {
				formControl[i].style = 'box-shadow:  0 0 0 2px rgba(13, 110, 253, .25)'
			} else {
				formControl[i].style = 'box-shadow: 0 0 0 2px rgba(255,79,44,0.4);'
			}

			formControl[i].addEventListener("blur", () => {
				if (formControl[i].style.boxShadow == 'rgba(13, 110, 253, 0.25) 0px 0px 0px 2px') {
					formControl[i].style = 'box-shadow:  none'
				}
			})
		})


		formControl[i].addEventListener("focus", () => {
			if (formControl[i].style.boxShadow != 'rgba(255, 79, 44, 0.4) 0px 0px 0px 2px') {
				formControl[i].style = 'box-shadow:  0 0 0 2px rgba(13, 110, 253, .25)'
			}
		})
	}

	return check
}

var btnCreate = document.querySelector(".btnCreate")
btnCreate.addEventListener("click", createOrder)

function createOrder() {
	if (validateForm()) {
		var formData = new FormData()
		var phoneSender = document.querySelector(".phoneSender")
		var fullnameSender = document.querySelector(".fullnameSender")
		var phoneReceiver = document.querySelector(".phoneReceiver")
		var fullnameReceiver = document.querySelector(".fullnameReceiver")
		var addressReceiver = document.querySelector(".addressReceiver")
		var fileImage = document.querySelectorAll(".fileImage")
		var nameP = document.querySelectorAll(".nameP")
		var massP = document.querySelectorAll(".massP")
		var quantityP = document.querySelectorAll(".quantityP")
		var notes = document.querySelector(".notes")
		var warehouse = document.querySelector(".warehouse")

		formData.set("phoneSender", phoneSender.value)
		formData.set("fullnameSender", fullnameSender.value)
		formData.set("phoneReceiver", phoneReceiver.value)
		formData.set("fullnameReceiver", fullnameReceiver.value)
		formData.set("addressReceiver", addressReceiver.value)
		for (let i = 0; i < fileImage.length; i++) {
			for (let j = 0; j < fileImage[i].files.length; j++) {
				formData.append("fileImage", fileImage[i].files[j])
			}
		}
		for (let i = 0; i < nameP.length; i++) {
			formData.append("nameP", nameP[i].value)
		}
		for (let i = 0; i < massP.length; i++) {
			formData.append("massP", massP[i].value)
		}
		for (let i = 0; i < quantityP.length; i++) {
			formData.append("quantityP", quantityP[i].value)
		}
		formData.append("notes", notes.value)
		formData.append("warehouse", warehouse.value)


		$.ajax({
			url: "/package/createOrder",
			type: "post",
			processData: false,  // Không xử lý dữ liệu
			contentType: false,  // Không cần ghi đè contentType
			data: formData,
			success: function(response, status, xhr) {
				if (response == 'success') {
					swal("", "Tạo đơn hàng thành công !", 'success')
					resetForm()
				}
			}
		})
	}
}

var btnReset = document.querySelector(".btnReset")
btnReset.addEventListener("click", () => {
	location.href = "/package/createOrder"
})

var warehouseId = document.querySelector(".warehouse").value;
function resetForm() {
	var formControl = document.querySelectorAll(".form-control")
	var formAddP = document.querySelectorAll(".form-addProduct")
	var fileImage = document.querySelectorAll(".fileImage")
	var warehouse = document.querySelector(".warehouse");

	formControl.forEach((e, i) => {
		formControl[i].value = ''
		formControl[i].style = 'box-shadow:  none'
	})

	for (let i = 0; i < formAddP.length - 1; i++) {
		formAddP[i].remove()
	}

	fileImage.forEach((e, i) => {
		fileImage[i].value = ''
	})

	warehouse.value = warehouseId

	var faCamera = document.querySelectorAll(".fa-camera")[0];
	var showImage = document.querySelectorAll(".showImage")[0];
	var createForms = document.querySelectorAll(".create-form")[0]
	var deleteForms = document.querySelectorAll(".delete-form")[0]
	var lbFileImage = document.querySelectorAll(".lbFileImage")[0]
	showImage.style.display = "none"
	faCamera.style.display = "block"

	createForms.style.display = "flex"
	deleteForms.style.display = "none"
	lbFileImage.style.boxShadow = 'none'
}


// update order
var btnUpdate = document.querySelector(".btnUpdate")
var packageId = document.querySelector(".packageId")
var faCamera = document.querySelectorAll(".fa-camera");
var btnCreate = document.querySelector(".btnCreate");
var imageName = document.querySelectorAll(".showImage")

if (packageId) {
	var createForm = document.querySelectorAll(".create-form")
	var deleteForm = document.querySelectorAll(".delete-form")

	for (let i = 0; i < createForm.length - 1; i++) {
		deleteForm[i].style.display = "flex"
		createForm[i].style.display = "none"
	}

	for (let i = 0; i < faCamera.length; i++) {
		faCamera[i].style.display = "none"
	}

	btnCreate.removeEventListener("click", createOrder)
	btnCreate.style.opacity = '0.3'

	btnUpdate.addEventListener("click", updateOrder)
} else {
	btnUpdate.style.opacity = '0.3'
	btnUpdate.removeEventListener("click", updateOrder)
}

function updateOrder() {
	var packageId = document.querySelector(".packageId")
	var id = packageId.getAttribute("id")

	var formData = new FormData()
	var phoneSender = document.querySelector(".phoneSender")
	var fullnameSender = document.querySelector(".fullnameSender")
	var phoneReceiver = document.querySelector(".phoneReceiver")
	var fullnameReceiver = document.querySelector(".fullnameReceiver")
	var addressReceiver = document.querySelector(".addressReceiver")
	var fileImage = document.querySelectorAll(".fileImage")
	var nameP = document.querySelectorAll(".nameP")
	var massP = document.querySelectorAll(".massP")
	var quantityP = document.querySelectorAll(".quantityP")
	var notes = document.querySelector(".notes")
	var warehouse = document.querySelector(".warehouse")
	var imageName = document.querySelectorAll(".showImage")

	formData.set("packageId", id)
	formData.set("phoneSender", phoneSender.value)
	formData.set("fullnameSender", fullnameSender.value)
	formData.set("phoneReceiver", phoneReceiver.value)
	formData.set("fullnameReceiver", fullnameReceiver.value)
	formData.set("addressReceiver", addressReceiver.value)

	for (let i = 0; i < imageName.length; i++) {
		if (imageName[i].getAttribute("imageName") != null) {
			formData.append("imageName", imageName[i].getAttribute("imageName"))
		}
	}

	for (let i = 0; i < fileImage.length; i++) {
		for (let j = 0; j < fileImage[i].files.length; j++) {
			formData.append("fileImage", fileImage[i].files[j])
		}
	}
	for (let i = 0; i < nameP.length; i++) {
		formData.append("nameP", nameP[i].value)
	}
	for (let i = 0; i < massP.length; i++) {
		formData.append("massP", massP[i].value)
	}
	for (let i = 0; i < quantityP.length; i++) {
		formData.append("quantityP", quantityP[i].value)
	}
	formData.append("notes", notes.value)
	formData.append("warehouse", warehouse.value)


	$.ajax({
		url: "/package/updateOrder",
		type: "post",
		processData: false,  // Không xử lý dữ liệu
		contentType: false,  // Không cần ghi đè contentType
		data: formData,
		success: function(response, status, xhr) {
			if (response == 'success') {
				swal("", "Cập nhật đơn hàng thành công !", 'success')
			}
		}
	})
}









