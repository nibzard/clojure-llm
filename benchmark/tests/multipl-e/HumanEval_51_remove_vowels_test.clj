(require '[clojure.test :refer [deftest is run-test]])

(def candidate remove_vowels)

(deftest test-humaneval

  (is (= (candidate "") ""))
  (is (= (candidate "abcdef
ghijklm") "bcdf
ghjklm"))
  (is (= (candidate "fedcba") "fdcb"))
  (is (= (candidate "eeeee") ""))
  (is (= (candidate "acBAA") "cB"))
  (is (= (candidate "EcBOO") "cB"))
  (is (= (candidate "ybcd") "ybcd"))
)

(run-test test-humaneval)