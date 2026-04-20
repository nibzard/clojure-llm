(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [255 0 0] (hsv-to-rgb 0 1 1)))
  (is (= [0 255 0] (hsv-to-rgb 120 1 1)))
  (is (= [0 0 255] (hsv-to-rgb 240 1 1)))
  (is (= [255 255 0] (hsv-to-rgb 60 1 1))))

(run-test test-variation)
