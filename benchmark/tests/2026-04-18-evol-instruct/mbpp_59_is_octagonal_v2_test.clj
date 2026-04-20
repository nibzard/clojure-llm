(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 1 (nth-octagonal 1)))
  (is (= 28 (nth-octagonal 4)))
  (is (= 145 (nth-octagonal 10))))

(run-test test-variation)
