(require '[clojure.test :refer [deftest is run-test]])

(def candidate sort_sublists)

(deftest test-humaneval

  (is (= (candidate [["green" "orange"] ["black" "white"] ["white" "black" "orange"]]) [["green" "orange"] ["black" "white"] ["black" "orange" "white"]]))
  (is (= (candidate [[" red " "green"] ["blue " " black"] [" orange" "brown"]]) [[" red " "green"] [" black" "blue "] [" orange" "brown"]]))
  (is (= (candidate [["zilver" "gold"] ["magnesium" "aluminium"] ["steel" "bronze"]]) [["gold" "zilver"] ["aluminium" "magnesium"] ["bronze" "steel"]]))
)

(run-test test-humaneval)