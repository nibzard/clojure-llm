(require '[clojure.test :refer [deftest is run-test]])

(def candidate valid_date)

(deftest test-humaneval

  (is (= (candidate "03-11-2000") true))
  (is (= (candidate "15-01-2012") false))
  (is (= (candidate "04-0-2040") false))
  (is (= (candidate "06-04-2020") true))
  (is (= (candidate "01-01-2007") true))
  (is (= (candidate "03-32-2011") false))
  (is (= (candidate "") false))
  (is (= (candidate "04-31-3000") false))
  (is (= (candidate "06-06-2005") true))
  (is (= (candidate "21-31-2000") false))
  (is (= (candidate "04-12-2003") true))
  (is (= (candidate "04122003") false))
  (is (= (candidate "20030412") false))
  (is (= (candidate "2003-04") false))
  (is (= (candidate "2003-04-12") false))
  (is (= (candidate "04-2003") false))
)

(run-test test-humaneval)