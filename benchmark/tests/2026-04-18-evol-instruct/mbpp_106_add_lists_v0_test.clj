(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= {:a 1, :b 2, :c 3} (zip-map [:a :b :c] [1 2 3]))))

(run-test test-variation)
