import React, { useState, useEffect } from "react";
import api from "../config/axios";
import { useNavigate, Link } from "react-router-dom";
import "../styles/Estate.css";
import SearchBar from "../components/SearchBar";

const EstateOutstanding = () => {
    const [estates, setEstates] = useState([]);
    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState("");
    const [sortBy, setSortBy] = useState(null);

    const navigate = useNavigate();

    useEffect(() => {
        fetchEstates();
    }, []);

    const handleSearchResults = (results) => {
        setEstates(results);
    };

    const fetchEstates = async (search = "") => {
        setIsLoading(true);
        try {
            const response = await api.get(`/search?query=${search}`);
            const approvedEstates = response.data.filter(
                (estate) => estate.estateStatus === "APPROVED"
            );
            setEstates(approvedEstates);
        } catch (err) {
            console.error("Có lỗi trong quá trình lấy dữ liệu căn hộ:", err);
            setError(
                err.response?.data?.message || "Lỗi khi lấy dữ liệu căn hộ."
            );
        } finally {
            setIsLoading(false);
        }
    };

    const handleEstateClick = async (estateId) => {
        navigate(`/showEstateDetail/${estateId}`);
    };

    const formatCurrency = (amount) => {
        return new Intl.NumberFormat("vi-VN", {
            style: "currency",
            currency: "VND",
            minimumFractionDigits: 0,
        })
            .format(amount)
            .replace("₫", "đ");
    };

    const handleSort = (criteria) => {
        setSortBy(criteria);
        let sortedEstates = [...estates];
        if (criteria === "price") {
            sortedEstates.sort((a, b) => a.price - b.price);
        } else if (criteria === "name") {
            sortedEstates.sort((a, b) => a.title.localeCompare(b.title));
        }
        setEstates(sortedEstates);
    };

    if (isLoading) return <div aria-live="polite">Đang tải...</div>;
    if (error) return <div>Error: {error}</div>;

    return (
        <div className="timeshare-estates-container estate-show-container">
            {/* <SearchBar onSearch={handleSearchResults} /> */}
            <div className="sort-options">
                <button onClick={() => handleSort("price")}>
                    Sắp xếp theo giá
                </button>
                <button onClick={() => handleSort("name")}>
                    Sắp xếp theo tên
                </button>
            </div>
            <div className="estates">
                {estates.map((estate) => (
                    <div key={estate.id} className="estate-card">
                        <button
                            style={{ all: "unset", cursor: "pointer" }}
                            onClick={() => handleEstateClick(estate.id)}
                        >
                            <img
                                className="estate-image"
                                src={
                                    estate.resources?.[0]?.url ||
                                    "placeholder_image_url"
                                }
                                alt={estate.title || "Estate"}
                            />
                            <div className="estate-info">
                                <h5>
                                    <strong>{estate.title}</strong> |{" "}
                                    {estate.location}
                                </h5>
                                <p>
                                    <strong>Giá:</strong>{" "}
                                    {formatCurrency(estate.price)}
                                </p>
                                <p>
                                    <strong>Thời gian nhận phòng:</strong>{" "}
                                    {estate.checkIn || "Not specified"}
                                </p>
                                <p>
                                    <strong>Thời gian trả phòng:</strong>{" "}
                                    {estate.checkOut || "Not specified"}
                                </p>
                            </div>
                        </button>
                    </div>
                ))}
            </div>
            <Link to="/show-estate" className="hero-btn">
                Xem Thêm
            </Link>
        </div>
    );
};

export default EstateOutstanding;
