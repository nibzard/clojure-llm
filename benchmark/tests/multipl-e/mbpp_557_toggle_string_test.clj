(require '[clojure.test :refer [deftest is run-test]])

(def candidate toggle_string)

(deftest test-humaneval

  (is (= (candidate "Python") "pYTHON"))
  (is (= (candidate "Pangram") "pANGRAM"))
  (is (= (candidate "LIttLE") "liTTle"))
)

(run-test test-humaneval)