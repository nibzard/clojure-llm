(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= "#ff00aa" (rgb-to-hex 255 0 170))))

(run-test test-variation)
