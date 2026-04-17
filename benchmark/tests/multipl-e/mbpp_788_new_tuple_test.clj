(require '[clojure.test :refer [deftest is run-test]])

(def candidate new_tuple)

(deftest test-humaneval

  (is (= (candidate ["WEB" "is"] "best") ["WEB" "is" "best"]))
  (is (= (candidate ["We" "are"] "Developers") ["We" "are" "Developers"]))
  (is (= (candidate ["Part" "is"] "Wrong") ["Part" "is" "Wrong"]))
)

(run-test test-humaneval)