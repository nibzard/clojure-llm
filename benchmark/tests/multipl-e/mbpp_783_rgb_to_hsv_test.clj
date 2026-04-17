(require '[clojure.test :refer [deftest is run-test]])

(def candidate rgb_to_hsv)

(deftest test-humaneval

  (is (= (candidate 255 255 255) [0.0 0.0 100.0]))
  (is (= (candidate 0 215 0) [120.0 100.0 84.31372549019608]))
  (is (= (candidate 10 215 110) [149.26829268292684 95.34883720930233 84.31372549019608]))
)

(run-test test-humaneval)