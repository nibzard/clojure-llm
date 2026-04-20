(defn strlen
  " Return length of given string
  >>> (strlen "")
  0
  >>> (strlen "abc")
  3"
  [string]
  (count (or string "")))