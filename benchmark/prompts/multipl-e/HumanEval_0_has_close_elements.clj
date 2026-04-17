(defn has_close_elements
  " Check if in given list of numbers, are any two numbers closer to each other than
  given threshold.
  >>> (has_close_elements [1.0 2.0 3.0] 0.5)
  false
  >>> (has_close_elements [1.0 2.8 3.0 4.0 5.0 2.0] 0.3)
  true"
  [numbers threshold]
  