(require '[clojure.test :refer [deftest is run-test]])

(def candidate max_run_uppercase)

(deftest test-humaneval

  (is (= (candidate "GeMKSForGERksISBESt") 5))
  (is (= (candidate "PrECIOusMOVemENTSYT") 6))
  (is (= (candidate "GooGLEFluTTER") 4))
)

(run-test test-humaneval)