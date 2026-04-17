(require '[clojure.test :refer [deftest is run-test]])

(def candidate fix_spaces)

(deftest test-humaneval

  (is (= (candidate "Example") "Example"))
  (is (= (candidate "Mudasir Hanif ") "Mudasir_Hanif_"))
  (is (= (candidate "Yellow Yellow  Dirty  Fellow") "Yellow_Yellow__Dirty__Fellow"))
  (is (= (candidate "Exa   mple") "Exa-mple"))
  (is (= (candidate "   Exa 1 2 2 mple") "-Exa_1_2_2_mple"))
)

(run-test test-humaneval)