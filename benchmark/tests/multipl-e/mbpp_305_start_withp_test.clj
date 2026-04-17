(require '[clojure.test :refer [deftest is run-test]])

(def candidate start_withp)

(deftest test-humaneval

  (is (= (candidate ["Python PHP" "Java JavaScript" "c c++"]) ["Python" "PHP"]))
  (is (= (candidate ["Python Programming" "Java Programming"]) ["Python" "Programming"]))
  (is (= (candidate ["Pqrst Pqr" "qrstuv"]) ["Pqrst" "Pqr"]))
)

(run-test test-humaneval)