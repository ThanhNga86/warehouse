formProduct()
function formProduct() {
	var formProduct = document.querySelector(".form-product")

	createForm()
	function createForm() {
		var createForms = document.querySelectorAll(".create-form")
		var deleteForms = document.querySelectorAll(".delete-form")
		
		createForms.forEach((e, i) => {
			e.addEventListener("click", () => {
				createForms[i].style.display = 'none'
				if (deleteForms[i]) {
					deleteForms[i].style.display = 'flex'
				}
				var formAddProduct = document.createElement('div');
				formAddProduct.setAttribute('class', 'form-addProduct mb-2');
				formProduct.appendChild(formAddProduct)

				formAddProduct.innerHTML = `
					<div class="uploadImage">
						<input type="file" name="fileImage" class="fileImage">
						<label class="lbFileImage">
							<i class="fa-solid fa-camera"></i>
							<img class="showImage">
						</label>
						<div class="message msProduct"></div>
					</div>
					
					<div class="form-right">
						<div class="form-group mx-2">
							<input type="text" name="name" class="form-control" placeholder="Nhập tên sản phẩm">
							<div class="message msNameProduct"></div>
						</div>
						<div class="form-group mx-2">
							<input type="number" name="name" class="form-control" placeholder="Nhập khối lượng">
							<div class="message msMass"></div>
						</div>
						<div class="form-group mx-2">
							<input type="number" name="name" class="form-control" placeholder="Nhập số lượng">
							<div class="message msQuantity"></div>
						</div>
					</div>
					
					<div class="create-form"><i class="fa-solid fa-plus"></i></div>
					<div class="delete-form"><i class="fa-solid fa-xmark"></i></div>
				`
				createForm()
				deleteForm()
				uploadFileImage()
			})
		})
	}

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
						showImage[i].src = URL.createObjectURL(fileImage[i].files[0]);
						faCamera[i].style.display = "none"
					} else {
						message('Vui lòng chọn một hình ảnh có định dạng hợp lệ và kích thước không quá 20 MB.', "warming");
					}
				}
			})
		})
	}
}