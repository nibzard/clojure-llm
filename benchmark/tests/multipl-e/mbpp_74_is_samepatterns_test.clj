(require '[clojure.test :refer [deftest is run-test]])

(def candidate is_samepatterns)

(deftest test-humaneval

  (is (= (candidate ["red" "green" "green"] ["a" "b" "b"]) true))
  (is (= (candidate ["red" "green" "greenn"] ["a" "b" "b"]) false))
  (is (= (candidate ["red" "green" "greenn"] ["a" "b"]) false))
)

(run-test test-humaneval)