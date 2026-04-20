(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= {:a 1, :b 2, :c 3} (zipvecs->map [:a :b :c] [1 2 3])))
  (is (= {:a 1} (zipvecs->map [:a :b] [1])))
  (is (= {} (zipvecs->map nil [1 2]))))

(run-test test-variation)
