(require '[clojure.test :refer [deftest is run-test]])

(def candidate find_literals)

(deftest test-humaneval

  (is (= (candidate "The quick brown fox jumps over the lazy dog." "fox") ["fox" 16 19]))
  (is (= (candidate "Its been a very crazy procedure right" "crazy") ["crazy" 16 21]))
  (is (= (candidate "Hardest choices required strongest will" "will") ["will" 35 39]))
)

(run-test test-humaneval)