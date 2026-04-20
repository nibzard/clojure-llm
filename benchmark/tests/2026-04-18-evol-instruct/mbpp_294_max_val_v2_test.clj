(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 7 (max-positive [1 -2 7 nil 4])))
  (is (= nil (max-positive ["a" nil -3 0])))
  (is (= nil (max-positive []))))

(run-test test-variation)
