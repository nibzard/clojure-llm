(require '[clojure.test :refer [deftest is run-test]])

(def candidate lcs_of_three)

(deftest test-humaneval

  (is (= (candidate "AGGT12" "12TXAYB" "12XBA") 2))
  (is (= (candidate "Reels" "Reelsfor" "ReelsforReels") 5))
  (is (= (candidate "abcd1e2" "bc12ea" "bd1ea") 3))
)

(run-test test-humaneval)