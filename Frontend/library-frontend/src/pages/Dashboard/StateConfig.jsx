import EventAvailableIcon from "@mui/icons-material/EventAvailable";
import HistoryIcon from "@mui/icons-material/History";
import LibraryBooksIcon from '@mui/icons-material/LibraryBooks';
import TrendingUpIcon from "@mui/icons-material/TrendingUp";

export const statsConfig = ({ myLoans , reservations , stats  }) => [
  {
    id: "loans",
    title: "Current Loans",
    subtitle: "Books you're reading",
    value: myLoans.length,
    icon: <LibraryBooksIcon sx={{ fontSize: 32, color: "#4F46E5" }} />,
    bgColor: "bg-indigo-100",
    textColor: "text-indigo-600"
  },
  {
    id: "reservations",
    title: "Reservations",
    subtitle: "Books on hold",
    value: reservations?.length || 0,
    icon: <EventAvailableIcon sx={{ fontSize: 32, color: "#9333EA" }} />,
    bgColor: "bg-purple-100",
    textColor: "text-purple-600"
  },
  {
    id: "read",
    title: "Books Read",
    subtitle: "This Year",
    value: stats?.booksReadThisYear || 0,
    icon: <HistoryIcon sx={{ fontSize: 32, color: "#10B981" }} />,
    bgColor: "bg-green-100",
    textColor: "text-green-600"
  },
  {
    id: "streak",
    title: "Day Streak",
    subtitle: "Keep it Going!",
    value: stats?.readingStreak || 0,
    icon: <TrendingUpIcon sx={{ fontSize: 32, color: "#F59E0B" }} />,
    bgColor: "bg-orange-100",
    textColor: "text-orange-600"
  }
];
