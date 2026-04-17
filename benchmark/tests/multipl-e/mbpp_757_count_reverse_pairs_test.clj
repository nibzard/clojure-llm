(require '[clojure.test :refer [deftest is run-test]])

(def candidate count_reverse_pairs)

(deftest test-humaneval

  (is (= (candidate ["julia" "best" "tseb" "for" "ailuj"]) 2))
  (is (= (candidate ["geeks" "best" "for" "skeeg"]) 1))
  (is (= (candidate ["makes" "best" "sekam" "for" "rof"]) 2))
)

(run-test test-humaneval)