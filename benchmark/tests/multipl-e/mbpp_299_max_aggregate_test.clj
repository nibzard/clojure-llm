(require '[clojure.test :refer [deftest is run-test]])

(def candidate max_aggregate)

(deftest test-humaneval

  (is (= (candidate [["Juan Whelan" 90] ["Sabah Colley" 88] ["Peter Nichols" 7] ["Juan Whelan" 122] ["Sabah Colley" 84]]) ["Juan Whelan" 212]))
  (is (= (candidate [["Juan Whelan" 50] ["Sabah Colley" 48] ["Peter Nichols" 37] ["Juan Whelan" 22] ["Sabah Colley" 14]]) ["Juan Whelan" 72]))
  (is (= (candidate [["Juan Whelan" 10] ["Sabah Colley" 20] ["Peter Nichols" 30] ["Juan Whelan" 40] ["Sabah Colley" 50]]) ["Sabah Colley" 70]))
)

(run-test test-humaneval)