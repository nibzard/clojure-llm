(defn polygon_area
  "Given a vector of side lengths for a polygon, return the area using the shoelace formula
  if the vector contains an even number of coordinates representing points in the form
  [x1 y1 x2 y2 ... xn yn]. Return -1 if the input does not contain at least 3 points.
  
  Example:
  >>> (polygon_area [0 0 4 0 4 3 0 3])
  12.0
  >>> (polygon_area [0 0 1 1 2 2 3 3])
  -1"
  [coords])