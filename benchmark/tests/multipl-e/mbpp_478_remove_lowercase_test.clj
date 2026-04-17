(require '[clojure.test :refer [deftest is run-test]])

(def candidate remove_lowercase)

(deftest test-humaneval

  (is (= (candidate "PYTHon") "PYTH"))
  (is (= (candidate "FInD") "FID"))
  (is (= (candidate "STRinG") "STRG"))
)

(run-test test-humaneval)