#!/usr/bin/env python

import sys
import Image
import ImageQt
from PyQt4 import QtCore, QtGui
from HeadGen import *

class AvatarDialog(QtGui.QDialog):            
    def __init__(self, parent=None):
        QtGui.QDialog.__init__(self, parent)
        #set size and title
        self.setGeometry(50, 50, 400, 300)
        self.setWindowTitle('Choose avatar')
        #set background color
        palette = QtGui.QPalette()
        palette.setColor(QtGui.QPalette.Window, QtGui.QColor(53,53,53));
        palette.setColor(QtGui.QPalette.Button, QtGui.QColor(53,53,53));
        palette.setColor(QtGui.QPalette.Highlight, QtGui.QColor(142,45,197));
        palette.setColor(QtGui.QPalette.ButtonText, QtGui.QColor(255,255,255));
        palette.setColor(QtGui.QPalette.WindowText, QtGui.QColor(255,255,255));
        self.setPalette(palette)
        #create initial avatar
        self.isGravatar = False
        self.head = 1
        self.body = 2
        self.R = 145
        self.G = 192
        self.B = 237
        self.email = 'abc@abc.com'
        self.avatar = QtGui.QLabel()
        #self.avatar.setPixmap(QtGui.QPixmap.fromImage(HeadGen('1.2.145.192.237', 0)))
        self.avatar.setPixmap(self.getAvatar())
        self.avatar.setMaximumHeight(75)
        self.avatar.setAlignment(QtCore.Qt.AlignCenter)
        #create type button
        self.useGravatar = QtGui.QRadioButton('Use gravatar')
        self.useDRRR = QtGui.QRadioButton('Use Durarara style avatar')
        self.useDRRR.setChecked(True)
        #create Gravarta text area
        self.emailEdit = QtGui.QLineEdit()
        self.emailEdit.setText(self.email)
        #create DRRR button
        self.headButton = list()
        for num in range(0, 15):
            self.headButton.append(QtGui.QRadioButton())
            self.headButton[num].setIcon(QtGui.QIcon('Head/%02d.png' % (num+1)))
            self.headButton[num].setIconSize(QtCore.QSize(50, 50))
        self.headButton[self.head-1].setChecked(True)
        self.bodyButton = list()
        for num in range(0, 14):
            self.bodyButton.append(QtGui.QRadioButton())
            self.bodyButton[num].setIcon(QtGui.QIcon('Body/%02d.png' % (num+1)))
            self.bodyButton[num].setIconSize(QtCore.QSize(50, 50))
        self.bodyButton[self.body-1].setChecked(True)
        self.REdit = QtGui.QSpinBox()
        self.REdit.setMinimum(0)
        self.REdit.setMaximum(255)
        self.REdit.setValue(self.R)
        self.GEdit = QtGui.QSpinBox()
        self.GEdit.setMinimum(0)
        self.GEdit.setMaximum(255)
        self.GEdit.setValue(self.G)
        self.BEdit = QtGui.QSpinBox()
        self.BEdit.setMinimum(0)
        self.BEdit.setMaximum(255)
        self.BEdit.setValue(self.B)
        self.colorDialog = QtGui.QPushButton()
        self.colorDialog.setText('Choose...')
        self.colorDialog.setMaximumWidth(60)
        #Create dialog button
        self.dialogButton = QtGui.QDialogButtonBox()
        
        #create type group
        self.typeGroup = QtGui.QButtonGroup()
        self.typeGroup.setExclusive(True)
        self.typeGroup.addButton(self.useGravatar)
        self.typeGroup.addButton(self.useDRRR)
        #create head group
        self.headGroup = QtGui.QButtonGroup()
        self.headGroup.setExclusive(True)
        for num in range(0, 15):
            self.headGroup.addButton(self.headButton[num])
        #create body group
        self.bodyGroup = QtGui.QButtonGroup()
        self.bodyGroup.setExclusive(True)
        for num in range(0, 14):
            self.bodyGroup.addButton(self.bodyButton[num])
        #set layout
        emailLayout = QtGui.QHBoxLayout()
        emailLayout.addSpacing(20)
        emailLayout.addWidget(self.emailEdit)
        
        headLayout3 = QtGui.QHBoxLayout()
        for num in range(0, 8):
            headLayout3.addWidget(self.headButton[num])
        headLayout4 = QtGui.QHBoxLayout()
        for num in range(8, 15):
            headLayout4.addWidget(self.headButton[num])
        headLayout2 = QtGui.QVBoxLayout()
        headLayout2.addLayout(headLayout3)
        headLayout2.addLayout(headLayout4)
        headLayout1 = QtGui.QHBoxLayout()
        headLayout1.addSpacing(20)
        headLayout1.addLayout(headLayout2)
        
        bodyLayout3 = QtGui.QHBoxLayout()
        for num in range(0, 7):
            bodyLayout3.addWidget(self.bodyButton[num])
        bodyLayout4 = QtGui.QHBoxLayout()
        for num in range(7, 14):
            bodyLayout4.addWidget(self.bodyButton[num])
        bodyLayout2 = QtGui.QVBoxLayout()
        bodyLayout2.addLayout(bodyLayout3)
        bodyLayout2.addLayout(bodyLayout4)
        bodyLayout1 = QtGui.QHBoxLayout()
        bodyLayout1.addSpacing(20)
        bodyLayout1.addLayout(bodyLayout2)
        
        colorLayout = QtGui.QHBoxLayout()
        colorLayout.addSpacing(20)
        colorLayout.addWidget(self.REdit)
        colorLayout.addWidget(self.GEdit)
        colorLayout.addWidget(self.BEdit)
        colorLayout.addWidget(self.colorDialog)

        layout = QtGui.QVBoxLayout()
        layout.addWidget(self.avatar)
        layout.addWidget(self.useGravatar)
        layout.addLayout(emailLayout)
        layout.addWidget(self.useDRRR)
        layout.addLayout(headLayout1)
        layout.addLayout(bodyLayout1)
        layout.addLayout(colorLayout)
        layout.addWidget(self.dialogButton)

        self.setLayout(layout)
        
    def getAvatar(self):
        if self.isGravatar:
            return QtGui.QPixmap.fromImage(HeadGen(self.email, True))
        else:
            param = '%d.%d.%d.%d.%d' % (self.head, self.body, self.R, self.G, self.B)
            return QtGui.QPixmap.fromImage(HeadGen(param, False))
        

if __name__ == '__main__':
    app = QtGui.QApplication(sys.argv)
    avatar = AvatarDialog()
    avatar.show()
    sys.exit(app.exec_())