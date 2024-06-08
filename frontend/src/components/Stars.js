
import starFilled from './starImages/active.png';
import starEmpty from './starImages/nonactive.png';

// ...

function Stars({ rating }) {
    const MAX_RATING = 5;

    const filledStars = Array.from({ length: rating }, (_, i) => (
        <img key={i} src={starFilled} alt="Filled star" />
    ));

    const emptyStars = Array.from({ length: MAX_RATING - rating }, (_, i) => (
        <img key={i} src={starEmpty} alt="Empty star" />
    ));

    return (
        <div>
            {filledStars}
            {emptyStars}
        </div>
    );
}
export default Stars;