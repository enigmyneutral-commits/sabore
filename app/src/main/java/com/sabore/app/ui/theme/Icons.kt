package com.sabore.app.ui.theme

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SaboreIcon(
    emoji: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tintColor: Color = LocalContentColor.current,
    size: Dp = 24.dp
) {
    // Placeholder para ícone emoji - substitua por ImageView se necessário
    Unit
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SaboreIconButton(
    onClick: () -> Unit,
    emoji: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tintColor: Color = LocalContentColor.current
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Unit
    }
}

object SaboreIcons {
    // Navegação
    val Home = "🏠"
    val HomeFilled = "🏠"
    val Explore = "🔍"
    val Book = "📖"
    val People = "👥"
    val Person = "👤"
    val Settings = "⚙️"
    val ArrowBack = "◀"
    val ArrowForward = "▶"
    val ChevronRight = "▸"
    val ExitToApp = "🚪"
    val Menu = "☰"
    val MoreVert = "⋮"

    // Ações
    val Add = "➕"
    val Search = "🔍"
    val Send = "📤"
    val Refresh = "🔄"
    val Check = "✓"
    val CheckCircle = "✔"
    val Close = "✕"
    val Star = "⭐"
    val StarBorder = "☆"
    val Bookmark = "🔖"
    val PlayArrow = "▶"
    val Pause = "⏸"
    val Stop = "⏹"
    val Download = "⬇"
    val Upload = "⬆"
    val Share = "📤"

    // Alimentação / Cozinha
    val Restaurant = "🍽️"
    val Fastfood = "🍔"
    val Cake = "🍰"
    val LocalPizza = "🍕"
    val LocalCafe = "☕"
    val LunchDining = "🍽️"
    val DinnerDining = "🍽️"
    val BakeryDining = "🥖"
    val WineBar = "🍷"
    val LocalBar = "🍸"
    val LocalFlorist = "🌸"
    val Kitchen = "🍳"
    val FoodBank = "🥫"
    val ListAlt = "📋"
    val Receipt = "🧾"
    val ShoppingCart = "🛒"
    val LocalGroceryStore = "🛒"
    val LocalDining = "🍽️"
    val LocalPharmacy = "💊"

    // Chat / Assistente
    val Chat = "💬"
    val ChatBubble = "💬"
    val SmartToy = "🤖"
    val Robot = "🤖"
    val Help = "❓"
    val QuestionAnswer = "❓"
    val Email = "📧"
    val Phone = "📞"
    val Notifications = "🔔"
    val Bell = "🔔"
    val PersonAdd = "👤➕"

    // Perfil / Usuário
    val AccountCircle = "👤"
    val PersonOutline = "👤"
    val Face = "😊"
    val Group = "👥"
    val Groups = "👥"
    val Verify = "✓"
    val Shield = "🛡️"
    val Lock = "🔒"
    val PrivacyTip = "🔒"
    val Public = "🌍"
    val PrivateConnection = "🔐"

    // Pagamento / Assinatura
    val CreditCard = "💳"
    val Payment = "💳"
    val QrCode = "📱"
    val QrCode2 = "📱"
    val AttachMoney = "💵"
    val Money = "💵"
    val AccountBalance = "🏦"
    val AccountBalanceWallet = "🏦"
    val History = "📜"
    val Schedule = "📅"
    val Today = "📅"
    val CalendarToday = "📅"
    val AccessTime = "⏰"

    // Layout / Interface
    val ViewCompact = "▭"
    val ViewList = "📋"
    val GridView = "⌘"
    val Dashboard = "📊"
    val Folder = "📁"
    val FolderOpen = "📂"
    val Description = "📄"
    val Note = "📝"
    val Label = "🏷️"
    val Tag = "🏷️"
    val LocalOffer = "🏷️"
    val LocalFireDepartment = "🚒"
    val Warning = "⚠"
    val Error = "❌"
    val Info = "ℹ"
    val Favorite = "❤"
    val FavoriteBorder = "♡"
    val RateReview = "⭐"
    val StarRate = "⭐"
    val BarChart = "📊"
    val PieChart = "🥧"
    val InsertChart = "📊"
    val Image = "🖼️"
    val ImageSearch = "🔍"
    val CameraAlt = "📷"
    val Camera = "📷"
    val AddPhotoAlternate = "🖼️"
    val PhotoLibrary = "🖼️"
    val Palette = "🎨"
    val ColorLens = "🎨"
    val FormatColorFill = "🎨"
    val Bot = "🤖"
    val Memory = "💾"
    val Cpu = "💻"
    val Code = "💻"
}
