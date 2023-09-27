package winterry.part3.chapter4.mvp

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.load
import winterry.part3.chapter4.databinding.ActivityMvpBinding
import winterry.part3.chapter4.mvp.model.ImageCountModel
import winterry.part3.chapter4.mvp.repository.ImageRepositoryImpl

class MvpActivity: AppCompatActivity(), MvpContractor.View {

    private lateinit var binding: ActivityMvpBinding
    private lateinit var presenter: MvpContractor.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMvpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.view = this

        presenter = MvpPresenter(ImageCountModel(), ImageRepositoryImpl())
        presenter.attachView(this)
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    fun loadImage() {
        presenter.loadRandomImage()
    }

    override fun showImage(url: String, color: String) {
        binding.imageView.run {
            setBackgroundColor(Color.parseColor(color))
            load(url) {
                crossfade(300)
            }
        }
    }

    override fun showImageCountText(count: Int) {
        binding.imageCountTextView.text = "불러온 이미지 수: $count"
    }

}