
import { Chip } from "@mui/material";

const GetStatusChip = ({ status }) => {

    const configs = {
        ACTIVE: {
            label: "Active",
            color: "success"
        },
        OVERDUE: {
            label: "Overdue",
            color: "error"
        },
        PENDING: {
            label: "Pending",
            color: "warning"
        },
        READY: {
            label: "Ready for Pickup",
            color: "success"
        }
    };

    const config = configs[status] || {
        label: status,
        color: "default"
    };

    return (
        <Chip
            label={config.label}
            color={config.color}
            size="small"
            sx={{
                fontWeight: 600,
                fontSize: "0.7rem",
                height: 24,
                borderRadius: 2
            }}
        />
    );
};

export default GetStatusChip;
