(defn hsv-to-rgb
  "Convert an HSV color to RGB.

  Hue is expected in degrees in the range [0, 360), saturation and value in [0, 1].

  Examples:
  (hsv-to-rgb 0 1 1)     ;=> [255 0 0]
  (hsv-to-rgb 120 1 1)   ;=> [0 255 0]
  (hsv-to-rgb 240 1 1)   ;=> [0 0 255]
  (hsv-to-rgb 60 1 1)    ;=> [255 255 0]"
  [h s v]
  (let [c (* v s)
        h' (/ (mod h 360) 60.0)
        x (* c (- 1 (Math/abs (- (mod h' 2) 1))))
        [r1 g1 b1] (cond
                     (< h' 1) [c x 0]
                     (< h' 2) [x c 0]
                     (< h' 3) [0 c x]
                     (< h' 4) [0 x c]
                     (< h' 5) [x 0 c]
                     :else    [c 0 x])
        m (- v c)]
    [(int (Math/round (* 255 (+ r1 m))))
     (int (Math/round (* 255 (+ g1 m))))
     (int (Math/round (* 255 (+ b1 m))))]))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [255 0 0] (hsv-to-rgb 0 1 1)))
  (is (= [0 255 0] (hsv-to-rgb 120 1 1)))
  (is (= [0 0 255] (hsv-to-rgb 240 1 1)))
  (is (= [255 255 0] (hsv-to-rgb 60 1 1))))

(run-test test-variation)
