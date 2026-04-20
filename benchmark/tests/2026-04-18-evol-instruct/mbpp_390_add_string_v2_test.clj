(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= ["apple" "avocado"]
         (doall (filter-string #"^a" ["apple" "banana" "avocado"]))))
  (is (= ["foo" "foo"]
         (take 2 (filter-string #"o" (repeat "foo"))))))

(run-test test-variation)
