(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= {:a 1, :b 2} (zip-to-map [:a :b :c] [1 2])))
  (is (= {} (zip-to-map nil [1 2])))
  (is (= {0 10, 1 20, 2 30} (zip-to-map (range) [10 20 30]))))

(run-test test-variation)
