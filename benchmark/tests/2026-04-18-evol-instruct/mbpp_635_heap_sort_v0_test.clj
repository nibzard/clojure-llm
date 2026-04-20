(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [3 3 3 2 2 1] (sort-by-frequency [1 2 2 3 3 3])))
  (is (= [:a :a :b :b :c] (sort-by-frequency [:a :b :a :c :b])))
  (is (= nil (sort-by-frequency nil))))

(run-test test-variation)
