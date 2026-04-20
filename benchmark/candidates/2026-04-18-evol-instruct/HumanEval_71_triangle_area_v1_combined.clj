(defn polygon_area
  "Given a vector of side lengths for a polygon, return the area using the shoelace formula
  if the vector contains an even number of coordinates representing points in the form
  [x1 y1 x2 y2 ... xn yn]. Return -1 if the input does not contain at least 3 points.
  
  Example:
  >>> (polygon_area [0 0 4 0 4 3 0 3])
  12.0
  >>> (polygon_area [0 0 1 1 2 2 3 3])
  -1"
  [coords]
  (if (and (vector? coords)
           (even? (count coords))
           (>= (/ (count coords) 2) 3))
    (let [pts (partition 2 coords)
          pairs (partition 2 1 (conj (vec pts) (first pts)))
          area (/ (Math/abs
                   (reduce + (map (fn [[[x1 y1] [x2 y2]]]
                                    (- (* x1 y2) (* y1 x2)))
                                  pairs)))
                  2.0)]
      (double (Math/round (* area 100.0))))
    -1))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 12.0 (polygon_area [0 0 4 0 4 3 0 3])))
  (is (= -1 (polygon_area [0 0 1 1 2 2 3 3]))))

(run-test test-variation)
