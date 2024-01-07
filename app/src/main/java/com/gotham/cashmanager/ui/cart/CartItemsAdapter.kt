package com.gotham.cashmanager.ui.cart



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.gotham.cashmanager.R

private  lateinit var imageList:Array<Int>
 var sum: Double = 90.00;

 var cart = CartViewModel()


class CartItemsAdapter(private val cartItems:ArrayList<CartItems>) :
    RecyclerView.Adapter<CartItemsAdapter.CartItemsViewHolder>() {

    private var dataPassListener: DataPassListener? = null

    fun setDataPassListener(listener: DataPassListener) {
        this.dataPassListener = listener
    }

    fun passDataToParent(data: String) {
        dataPassListener?.onDataPassed(data)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)

        return CartItemsViewHolder(itemView)
    }



    override fun onBindViewHolder(holder: CartItemsViewHolder, position: Int) {
        val currentItem = cartItems[position]
        holder.itemName.text = currentItem.itemName
        holder.itemPrice.text = currentItem.price
        holder.itemTotal.text = setTot(currentItem.price.toDouble(), Integer.valueOf(holder.tv.text.toString()), holder.itemTotal )
        holder.sum.text = setTotSum(holder.sum, holder.itemTotal)


         imageList = arrayOf(R.drawable.nutella, R.drawable.cremefraiche, R.drawable.eggs, R.drawable.jus, R.drawable.legumes, R.drawable.riz, R.drawable.yaourt1)
       holder.imageView.setImageResource(imageList[currentItem.imageUrl]);

        holder.btnadd.setOnClickListener {  itemView ->
            incrementQuantity(holder.tv, holder.itemTotal ,holder.itemPrice.text.toString().toDouble(), holder.sum)
        }
        holder.btnsubstract.setOnClickListener {  itemView ->
            decrementQuantity(holder.tv,holder.itemTotal, holder.itemPrice.text.toString().toDouble(), holder.sum)
        }

    }

    override fun getItemCount(): Int {
        return cartItems.size
    }

    class CartItemsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val itemName: TextView = itemView.findViewById(R.id.txtcartitem)
        val itemPrice: TextView = itemView.findViewById(R.id.txtcartprice)
        val tv: TextView = itemView.findViewById(R.id.quantity)
        val  btnadd: Button = itemView.findViewById(R.id.btnadd)
        val  btnsubstract: Button = itemView.findViewById(R.id.btnsubstract)
        val imageView: ImageView = itemView.findViewById(R.id.imgVcartitem)
        val itemTotal: TextView = itemView.findViewById(R.id.txtcarttotalprice)
        val sum: TextView = itemView.findViewById(R.id.sum)
       // val totalht: TextView? = itemView.findViewById(R.id.totalht)!!



    }

    interface DataPassListener {
        fun onDataPassed(data: String)
    }


    fun addCartItems(cartItem: CartItems) {
        cartItems.add(cartItem)
        notifyItemInserted(cartItems.size - 1)
    }

    fun incrementQuantity(view: TextView, totView: TextView, price: Double, totsum: TextView) {
        var qty = Integer.valueOf(view.text.toString())
        var quantity =qty + 1
        cart.setSum(cart.sum.value?.plus(price) ?: 0.00)

        view.text = quantity.toString()
        setTot(price, quantity, totView);
        passDataToParent(String.format("%.2f",cart.sum.value))

    }

    fun decrementQuantity(view: TextView, totView: TextView, price: Double,totsum: TextView){
        var quantity = Integer.valueOf(view.text.toString())
        if (quantity > 0){
            var qty: Int =  quantity - 1 ;
            view.text = qty.toString()
            cart.setSum(cart.sum.value?.minus(price) ?: 0.00)

            setTot(price, qty, totView);
            passDataToParent(String.format("%.2f",cart.sum.value))
        }
    }

    fun setTot(price: Double, quantity: Int, view: TextView): String{
        var x : Double = price * quantity;
        view.text = String.format("%.2f", x)
        return String.format("%.2f", x);
    }

    fun setTotSum(view: TextView, tot:TextView): String{
        var x = view.text.toString().toDouble()
        var t = tot.text.toString().toDouble()
        var tot = x+t;
        cart.setSum(cart.sum.value?.plus(tot) ?: 0.00)
        view.text = String.format("%.2f", cart.sum.value);
        passDataToParent(String.format("%.2f",cart.sum.value))
        return String.format("%.2f", cart.sum.value);

    }





}
