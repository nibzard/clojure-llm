(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= {:h 0 :s 100 :l 50} (rgb->hsl {:r 255 :g 0 :b 0})))
  (is (= {:h 0 :s 0 :l 0} (rgb->hsl {:r 0 :g 0 :b 0})))
  (is (= {:h 0 :s 0 :l 100} (rgb->hsl {:r 255 :g 255 :b 255}))))

(run-test test-variation)
