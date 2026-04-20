(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= :a (majority-element [:a :b :a :a])))
  (is (= 2 (majority-element [1 2 3 2 2])))
  (is (= nil (majority-element [1 2 3 4]))))

(run-test test-variation)
