(require '[clojure.test :refer [deftest is run-test]])

(def candidate solve)

(deftest test-humaneval

  (is (= (candidate "AsDf") "aSdF"))
  (is (= (candidate "1234") "4321"))
  (is (= (candidate "ab") "AB"))
  (is (= (candidate "#a@C") "#A@c"))
  (is (= (candidate "#AsdfW^45") "#aSDFw^45"))
  (is (= (candidate "#6@2") "2@6#"))
  (is (= (candidate "#$a^D") "#$A^d"))
  (is (= (candidate "#ccc") "#CCC"))
)

(run-test test-humaneval)